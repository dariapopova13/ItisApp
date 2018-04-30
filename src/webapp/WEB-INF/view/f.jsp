
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<head>
    <title>Новости</title>
</head>
<body>
<div id="root">
    <div class="container">
        <div class="row">
            {{news}}
        </div>
    </div>
</div>
</body>

<script>

    var app = new Vue({
        el: '#root',
        data : {news: []},
        mounted(){
            this.fetchPosts();
        },
        methods: {
            fetchPosts(){
                axios.get("/news/0").then(function(response){
                    this.news = response.data;
                    console.log(response.data)

                }.bind(this));
            },
            getLink(post){
                return '/post/' + post.id;
            }
        }
    });
</script>
</html>