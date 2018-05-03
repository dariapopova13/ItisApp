package com.university.itis.itisapp.timetable.google.service;

import com.google.api.client.googleapis.apache.GoogleApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;

import com.university.itis.itisapp.timetable.google.helpers.Converter;
import com.university.itis.itisapp.timetable.google.model.TmpObject;
import com.university.itis.itisapp.timetable.google.model.TmpObjectBuilder;
import com.university.itis.itisapp.timetable.utils.GoogleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@PropertySource("classpath:google.properties")
public class GoogleApiService {

    private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    @Value("${application_name}")
    private String applicationName;
    @Value("${spreadsheet_id}")
    private String spreadsheetId;
    @Value("${time_coordinates}")
    private String timeCoordinates;
    @Value("${api_key}")
    private String apiKey;

    public BatchGetValuesResponse sendRequest() throws IOException {
        Sheets service = getSheetsService();
        if (service == null) return null;

        Sheets.Spreadsheets.Values.BatchGet request =
                service.spreadsheets().values().batchGet(spreadsheetId).setKey(apiKey);
        request.setRanges(getWeeklyCoordinates());
        return request.execute();
    }


    public List<TmpObject> sendRequest(String group, int day) throws IOException {
        int course = googleUtils.getCourse(group);
        if (course == 0) return null;
        Sheets service = getSheetsService();
        if (service == null) return null;

        Sheets.Spreadsheets.Values.BatchGet request =
                service.spreadsheets().values().batchGet(spreadsheetId).setKey(apiKey);

        request.setRanges(getDailyCoordinates(course, day));
        return sort(request.execute(), course);
    }

    private Sheets getSheetsService() {
        try {
            return new Sheets.Builder(GoogleApacheHttpTransport.newTrustedTransport(),
                    JSON_FACTORY, null)
                    .setApplicationName(applicationName)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Autowired
    private GoogleUtils googleUtils;
    @Autowired
    private Converter converter;

    private List<TmpObject> sort(BatchGetValuesResponse googleResponse, int course) {
        List<List<Object>> subjectsResponse = googleResponse.getValueRanges().get(0).getValues() == null
                ? new ArrayList<List<Object>>()
                : googleResponse.getValueRanges().get(0).getValues();

        List<TmpObject> sortedSubjects = new ArrayList<>();

        for (int i = 0; i < subjectsResponse.size(); i++) {
            String time = googleUtils.getTime(i);
            List<Object> currentSubjects = subjectsResponse.get(i);

//            if (currentSubjects.size() == 0) continue;
//            if (currentSubjects.size() == 1) {
//                sortedSubjects.add(new TmpObjectBuilder()
//                        .setContent(currentSubjects.get(0).toString())
//                        .setTime(time)
//                        .setCourse(course)
//                        .createTmpObject());
//            } else {
//                Object current = currentSubjects.get(groupNum - 1);
//                sortedSubjects.add(new TmpObjectBuilder()
//                        .setContent(current.toString())
//                        .setTime(time)
//                        .setCourse(course)
//                        .setGroup(group)
//                        .createTmpObject());
//            }

            int size = currentSubjects.size();
            for (int j = 0; j < size; j++) {
                Object current = currentSubjects.get(j);
                sortedSubjects.add(new TmpObjectBuilder()
                        .setContent(current.toString())
                        .setTime(time)
                        .setCourse(course)
                        .setGroup(converter.getGroup(course, j))
                        .createTmpObject());
            }
        }

        return sortedSubjects;
    }

    private List<String> getDailyCoordinates(int course, int day) {
        String[] coordinates = {"D3:L9", "N3:U9", "V3:AC9", "AD3:AK9"};
        int cNew1 = 3, cNew2 = 9;
        String coords = coordinates[course - 1];
        if (day > 1) {
            cNew1 = 3 + 7 * (day - 1);
            cNew2 = 9 + 7 * (day - 1);
        }

        if (course == 1 && day == 6)
            cNew2 = cNew2 - 1;
        coords = coords.replace("3", String.valueOf(cNew1));
        coords = coords.replace("9", String.valueOf(cNew2));

        return Arrays.asList(new String[]{coords});
    }

    private List<String> getWeeklyCoordinates() {
        String[] result = new String[]{"D3:L9",
                "D10:L16",
                "D17:L23",
                "D24:L30",
                "D31:L37",
                "D38:L43",
                "N3:U9",
                "N10:U16",
                "N17:U23",
                "N24:U30",
                "N31:U37",
                "N38:U44",
                "V3:AC9",
                "V10:AC16",
                "V17:AC23",
                "V24:AC30",
                "V31:AC37",
                "V38:AC44",
                "AD3:AK9",
                "AD10:AK16",
                "AD17:AK23",
                "AD24:AK30",
                "AD31:AK37",
                "AD38:AK44"};

        return Arrays.asList(result);
    }

}
