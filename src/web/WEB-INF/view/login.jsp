<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta charset="utf-8" lang="ru-RU">
<head>

    <title>Новости</title>
</head>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<body>
<template>
    <ul v-if="posts && posts.length">
        <li v-for="post of posts">
            <p><strong>{{post.title}}</strong></p>
            <%--<p>{{news.}}</p>--%>
        </li>
    </ul>

    <ul v-if="errors && errors.length">
        <li v-for="error of errors">
            {{error.message}}
        </li>
    </ul>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                news: [],
                errors: []
            }
        },

        // Fetches news when the component is created.
        created() {
            axios.get(`/new/month/11-502`)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.news = response.data
                })
                .catch(e => {
                    this.errors.push(e)
                })

            // async / await version (created() becomes async created())
            //
            // try {
            //   const response = await axios.get(`http://jsonplaceholder.typicode.com/posts`)
            //   this.news = response.data
            // } catch (e) {
            //   this.errors.push(e)
            // }
        }
    }
</script>
</html>