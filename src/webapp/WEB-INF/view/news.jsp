<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="navbar.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">

    <title>Новости</title>
    <!-- Bootstrap core CSS-->
    <link href="../../resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../../resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="../../resources/css/sb-admin.css" rel="stylesheet">
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">

<div class="content-wrapper" id="news-list">
    <div class="container-fluid">
            <ol>
                <li v-for="item in news">
                    {{ item.newsText }}
                </li>
            </ol>
    </div>
</div>
</body>

</html>

<script>
    var app  = new Vue({
        el: '#news-list',
        data: {news: []},
        mounted() {
            this.fetchPosts();
        },
        methods: {
            fetchPosts() {
                axios.get("/news/0").then(function (response) {
                    this.news = response.data;
                    console.log(response.data)

                }.bind(this));
            },
            getLink(post) {
                return '/post/' + post.id;
            }
        }
    });
</script>
