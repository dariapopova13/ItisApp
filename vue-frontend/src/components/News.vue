<template>
    <div id="news">

        <v-btn color="blue darken-3" to="/news/add" class="ml-3" dark>Добавить</v-btn>
        <v-btn color="blue darken-3" @click="hideFilter(false)" class="ml-3" dark>Фильтр</v-btn>

        <div class="mt-3 mb-3 ml-3 mb-5" v-bind:hidden="isFilter">
            <h1 class="headline primary--text">Добавить фильтр</h1>
            <v-card >

                <v-layout row class="ml-3 mt-3">
                    <v-flex xs5 sm5>
                        <v-menu
                                ref="from_"
                                lazy
                                :close-on-content-click="false"
                                v-model="from_"
                                transition="scale-transition"
                                offset-y
                                full-width
                                :nudge-right="40"
                                min-width="290px"
                                :return-value.sync="fromDate">
                            <v-text-field
                                    v-model="fromDate"
                                    slot="activator"
                                    label="С"
                                    prepend-icon="event"
                                    readonly>
                            </v-text-field>

                            <v-date-picker v-model="fromDate" no-title scrollable required>
                                <v-spacer></v-spacer>
                                <v-btn flat color="primary" @click="from_ = false">Отмена</v-btn>
                                <v-btn flat color="primary" @click="$refs.from_.save(fromDate)">OK</v-btn>
                            </v-date-picker>
                        </v-menu>
                    </v-flex>
                    <v-flex xs5 sm5>
                        <v-menu
                                class="ml-3"
                                ref="to_"
                                lazy
                                :close-on-content-click="false"
                                v-model="to_"
                                transition="scale-transition"
                                offset-y
                                full-width
                                :nudge-right="40"
                                min-width="290px"
                                :return-value.sync="toDate">
                            <v-text-field
                                    v-model="toDate"
                                    slot="activator"
                                    label="до"
                                    readonly>
                            </v-text-field>

                            <v-date-picker v-model="toDate" no-title scrollable required>
                                <v-spacer></v-spacer>
                                <v-btn flat color="primary" @click="to_ = false">Отмена</v-btn>
                                <v-btn flat color="primary" @click="$refs.to_.save(toDate)">OK</v-btn>
                            </v-date-picker>
                        </v-menu>
                    </v-flex>
                </v-layout>

                <v-flex xs10 sm10 class="ml-3">
                    <v-select
                            :items="years"
                            v-model="year"
                            clearable
                            label="Год обучения"
                            single-line
                            prepend-icon="grade"
                    ></v-select>
                </v-flex>

                <v-flex xs10 sm10  class="ml-3">
                    <v-text-field
                            v-model="filterNewsText"
                            label="Заголовок"
                            multi-line
                            rows="1"
                            auto-grow>
                    </v-text-field>
                </v-flex>

                <v-flex xs10 sm10 class="ml-3">
                    <v-text-field
                            v-model="filterTitle"
                            label="Текст"
                            multi-line
                            rows="1"
                            auto-grow>
                    </v-text-field>
                </v-flex>

                <v-btn class="mb-3"  @click="hideFilter(true)">Отмена</v-btn>
                <v-btn class="mb-3">Ок</v-btn>


            </v-card>
        </div>

        <v-card class="mt-3 ml-3">
            <v-list three-line v-for="(item, index) in news" :key="index">
                <template>
                    <v-list-tile>
                        <v-list-tile-avatar class="mt-0">
                            <v-avatar color="blue darken-4" size="25">
                                <span class="white--text subheader">{{item.year}} </span>
                            </v-avatar>
                        </v-list-tile-avatar>

                        <v-list-tile-content class="mt-0">
                            <v-list-tile-title v-html="item.title"></v-list-tile-title>
                            <v-list-tile-sub-title v-html="item.newsText"></v-list-tile-sub-title>
                            <v-list-tile-sub-title>Дедлайн: {{item.deadline}}</v-list-tile-sub-title>
                        </v-list-tile-content>

                        <v-list-tile-avatar class="mt-0 right">
                            <v-layout column>
                                <v-flex xs12 sm3>
                                    <v-btn @click="deleteNews(index, item.id)" flat icon color="grey darken-4">
                                        <v-icon>delete</v-icon>
                                    </v-btn>
                                </v-flex>
                                <v-flex xs12 sm3>
                                    <v-btn v-bind:to="getEdit(item.id)" flat icon color="blue darken-2">
                                        <v-icon>edit</v-icon>
                                    </v-btn>
                                </v-flex>
                            </v-layout>
                        </v-list-tile-avatar>


                    </v-list-tile>
                    <v-divider class="mt-0"/>
                </template>
            </v-list>
        </v-card>

    </div>
</template>

<script>
    import Icon from "vue-awesome/components/Icon";
    import axios from 'axios';
    import VPaginator from "vuejs-paginator-axios/src/VPaginator";
    import AddNews from "./AddNews";

    export default {
        components: {AddNews, VPaginator, Icon},
        data() {
            return {
                news: [
                    {
                        id: 1,
                        newsText: 'tgest',
                        title: "titgggggggggffffftitgggggggggffffftitgggggggggffff" +
                        "ftitgggggggggffffftitgggggggggffffftitgggggggggffffftitggg" +
                        "ggggggffffftitgggggggggffffftitgggggggggffffftitgggggggggfffffti" +
                        "tgggggggggffffftitgggggggggffffftitgggggggggffffftitgggggggggfffff" +
                        "titgggggggggfffff",
                        course: {},
                        year: 3,
                        deadline: 454685412
                    },
                    {
                        id: 2,
                        newsText: 'testtesttesttesttesttesttest' +
                        'testtesttesttesttesttesttesttesttesttesttesttest' +
                        'testtesttesttesttesttesttesttest' +
                        'testtesttesttesttesttesttesttesttesttesttesttesttest' +
                        'testtest',
                        title: "title",
                        course: {},
                        year: 2,
                        deadline: 454685412
                    },
                    {
                        id: 3,
                        newsText: 'test',
                        title: "title",
                        course: {},
                        year: 1,
                        deadline: 454685412
                    }
                ],

                page: 0,
                isFilter: true,
                fromDate: null,
                from_: false,
                toDate: null,
                to_: false,
                years: [1, 2, 3, 4],
                year: '0',

                filterNewsText: '',
                filterTitle: ''
            }

            // return {
            //     news: [],
            // }

        },
        created() {
            axios.get(`http://localhost:8080/news/0`)
                .then(response => {
                    this.news = response.data
                })
                .catch(e => {
                    this.errors.push(e)
                })
        },
        methods: {
            deleteNews(index, id) {
                this.news.splice(index, 1);
                axios.delete(`http://localhost:8080/news/delete/${id}`).catch(e => {
                    this.errors.push(e)
                })
            },
            hideFilter(show) {
                this.isFilter = show
            },
            getEdit(id) {
                return "/news/edit/" + id;
            }
        }
    }

</script>
<style>

</style>

