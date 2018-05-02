<template>
    <div><p class="headline primary--text">Редактировать новость</p>

        <form class="mt-3">
            <v-text-field
                    v-model="currentNews.title"
                    label="Заголовок"
                    :error-messages="errors.collect('title')"
                    v-validate="'required'"
                    data-vv-name="title"
                    required>

            </v-text-field>

            <v-text-field
                    v-model="currentNews.newsText"
                    label="Текст"
                    v-validate="'required'"
                    multi-line
                    rows="2"
                    :error-messages="errors.collect('newsText')"
                    auto-grow
                    data-vv-name="newsText"
                    required>
            </v-text-field>

            <v-flex xs12 sm6>
                <v-select
                        :items="years"
                        v-model="currentNews.year"
                        label="Год обучения"
                        single-line
                        clearable
                        prepend-icon="grade"
                ></v-select>
            </v-flex>
            <v-menu
                    ref="deadline"
                    lazy
                    :close-on-content-click="false"
                    v-model="deadline"
                    transition="scale-transition"
                    offset-y
                    full-width
                    :nudge-right="40"
                    min-width="290px"
                    :return-value.sync="currentNews.deadline">
                <v-text-field
                        v-model="currentNews.deadline"
                        slot="activator"
                        :error-messages="errors.collect('date')"
                        label="Дедлайн"
                        v-validate="'required'"
                        prepend-icon="event"
                        readonly>
                </v-text-field>

                <v-date-picker v-model="currentNews.deadline" no-title scrollable required>
                    <v-spacer></v-spacer>
                    <v-btn flat color="primary" @click="deadline = false">Отмена</v-btn>
                    <v-btn flat color="primary" @click="$refs.deadline.save(currentNews.deadline)">OK</v-btn>
                </v-date-picker>
            </v-menu>


            <v-btn class="mt-5 right" color="primary" @click="submit">submit</v-btn>
        </form>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        data: () => ({
            currentNews: {
                newsText: 'text',
                title: 'title',
                deadline: '2020-04-14',
                year: 1
            },
            years: [1, 2, 3, 4],
            deadline: false,

            valid: true,
            requiredRules: [
                v => !!v || 'Это поле обязательно для заполнения'
            ],
            dictionary: {
                custom: {
                    title: {
                        required: () => 'Введите заголовок',
                    },
                    newsText: {
                        required: () => 'Введите текст',
                    },
                    date: {
                        required: () => 'Выберите дату дедлайна',
                    }, deadline: {
                        required: () => 'Выберите дату дедлайна',
                    }
                }
            }
        }),
        mounted() {
            this.$validator.localize('ru', this.dictionary)
        },
        methods: {
            submit() {
                if (this.$validator.validateAll()) {

                    axios.post('`http://localhost:8080/news/add', this.newsDto)
                        .then(response => {
                            this.newNews = response.data;
                            if (this.newsDto.id != null) {
                                this.$router.push({redirect: '/news'});
                            }
                        })
                        .catch(e => {
                            this.errors.push(e)

                        })
                }
            }
        }, created() {
            axios.get(`http://localhost:8080/news/single/${ this.$route.params.id}`)
                .then(response => {
                    this.currentNews = response.data
                })
                .catch(e => {
                    this.errors.push(e)
                })
        }
    }
</script>

<codepen-resources lang="json">
    {
    "js": ["https://unpkg.com/vee-validate@latest/dist/vee-validate.js"]
    }
</codepen-resources>
