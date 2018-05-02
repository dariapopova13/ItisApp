<template>


    <v-layout id="professors">
        <v-flex>
            <v-card>
                <v-list two-line>
                    <template v-for="(prof, index) in professors">
                        <v-list-tile @click="">
                            <v-list-tile-content>
                                <v-list-tile-title> {{prof.name}} {{prof.surname}}</v-list-tile-title>
                                <v-list-tile-sub-title>{{prof.info}}</v-list-tile-sub-title>
                            </v-list-tile-content>
                        </v-list-tile>
                        <v-divider/>
                    </template>

                </v-list>
            </v-card>
        </v-flex>
    </v-layout>

</template>

<script>

    export default {
        name: "Professor",
        data() {
            return {
                resource_url: '/professors/',
                options: {
                    remote_data: 'nested.data',
                    remote_current_page: 'nested.current_page',
                    remote_last_page: 'nested.last_page',
                    remote_next_page_url: 'nested.next_page_url',
                    remote_prev_page_url: 'nested.prev_page_url',
                    next_button_text: 'Go Next',
                    previous_button_text: 'Go Back'
                },
                professors: [
                    {
                        name: "Дарья",
                        surname: "Попова",
                        info: "Info"
                    },
                    {
                        name: "Name",
                        surname: "Surname",
                        info: "Info"
                    },
                    {
                        name: "Name",
                        surname: "SurnameSurnameSurname" +
                        "SurnameSurnameSurnameSurname" +
                        "SurnameSurnameSurnameSurname" +
                        "SurnameSurname",
                        info: "Info"
                    }
                    , {
                        name: "Name",
                        surname: "Surname",
                        info: "Info"
                    }, {
                        name: "Name",
                        surname: "Surname",
                        info: "Info"
                    }, {
                        name: "Name",
                        surname: "Surname",
                        info: "Info"
                    }, {
                        name: "Name",
                        surname: "Surname",
                        info: "Info"
                    }, {
                        name: "Name",
                        surname: "Surname",
                        info: "Info"
                    }, {
                        name: "Name",
                        surname: "Surname",
                        info: "Info"
                    }, {
                        name: "Name",
                        surname: "Surname",
                        info: "Info"
                    }, {
                        name: "Name",
                        surname: "Surname",
                        info: "Info"
                    }, {
                        name: "Name",
                        surname: "Surname",
                        info: "Info"
                    }
                ]

            }
        }
    }
</script>

<style scoped>

</style>