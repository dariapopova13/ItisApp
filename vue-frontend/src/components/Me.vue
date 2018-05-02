<template>
    <div>
        <h2 class="headline">{{user.name}} {{user.surname}}</h2>

        <v-card color="blue lighten-5" class="mt-3">

            <md-card-content>
                <v-card-text>
                    <span>Логин: </span>
                    <span class="ml-3">{{user.login}}</span>
                    <v-divider/>
                    <span>Email: </span>
                    <span class="ml-3">{{user.email}}</span>
                    <v-divider/>
                    <span>Роль: </span>
                    <span class="ml-3">{{user.role}}</span>
                </v-card-text>
            </md-card-content>
        </v-card>

        <v-btn color="blue darken-3" class="mt-5 right" dark>Редактировать</v-btn>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                user: {
                    login: "Login",
                    role: "Role",
                    name: "Name",
                    surname: "Surname",
                    email: "Email"
                }
            }
        }


    }
</script>