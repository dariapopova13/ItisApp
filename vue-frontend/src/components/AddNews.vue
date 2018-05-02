<template>
    <div><p class="headline primary--text">Добавить новость</p>


        <form class="mt-3">
            <v-text-field
                    v-model="newsDto.title"
                    label="Заголовок"
                    :error-messages="errors.collect('title')"
                    v-validate="'required'"
                    data-vv-name="title"
                    required>

            </v-text-field>

            <v-text-field
                    v-model="newsDto.newsText"
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
                        v-model="newsDto.year"
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
                    :return-value.sync="newsDto.deadline">
                <v-text-field
                        v-model="newsDto.deadline"
                        slot="activator"
                        label="Дедлайн"
                        v-validate="'required'"
                        prepend-icon="event"
                        readonly>
                </v-text-field>

                <v-date-picker v-model="newsDto.deadline" no-title scrollable required>
                    <v-spacer></v-spacer>
                    <v-btn flat color="primary" @click="deadline = false">Отмена</v-btn>
                    <v-btn flat color="primary" @click="$refs.deadline.save(newsDto.deadline)">OK</v-btn>
                </v-date-picker>
            </v-menu>


            <v-btn color="primary" class="mt-5 right" @click="submit">Сохранить</v-btn>

        </form>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        data: () => ({

            newsDto: {
                id: null,
                deadline: null,
                year: 0,
                title: '',
                newsText: ''
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
                    console.log(this.newsDto);
                    let headers = {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json'
                    };
                    axios.post(`http://localhost:8080/news/add`, this.newsDto, headers)
                        .then(response => {
                            this.newsDto = response.data;
                            if (this.newsDto.id != null) {
                                this.$router.push({redirect: '/news'});
                            }
                        })
                        .catch(e => {
                            this.errors.push(e)

                        })
                }
            }

        }
    }
</script>

<codepen-resources lang="json">
    {
    "js": ["https://unpkg.com/vee-validate@latest/dist/vee-validate.js"]
    }
</codepen-resources>
