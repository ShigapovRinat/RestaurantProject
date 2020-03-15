<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <%@include file="BootstrapConnection.jsp" %>

    <title>Main</title>
</head>

<body>
<%@ include file="header.jsp" %>

<div class="container marketing" style="margin-top: 4%">


    <!-- START THE FEATURETTES -->

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading">О ресторане <br><span class="text-muted">Мы изменим ваше представление о еде.</span></h2>
            <p class="lead">Мы открылись в октябре 2019 года. В меню Вы можете найти блюда как русской и татарской кухни, так и более экзотических стран.</p>
        </div>
        <div class="col-md-5">
            <img class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" src="image/снаружи.jpg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 500x500"><title>Placeholder</title><rect width="100%" height="100%" fill="#eee"></rect><text x="50%" y="50%" fill="#aaa" dy=".3em">Вид снаружи</text></img>
        </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-7 order-md-2">
            <h2 class="featurette-heading">Персонал <br><span class="text-muted">Лучшие из лучших</span></h2>
            <p class="lead">В нашем ресторане творят свои кулинарные произведения лучшие повара не только России, но и всей Европы.</p>
        </div>
        <div class="col-md-5 order-md-1">
            <img class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" src="image/cooker.jpg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 500x500"><title>Placeholder</title><rect width="100%" height="100%" fill="#eee"></rect><text x="50%" y="50%" fill="#aaa" dy=".3em">Шеф-повар</text></img>
        </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading">Будущее <br><span class="text-muted">Лучшие из лучших</span></h2>
            <p class="lead">На данный момент в нашем ресторане действует только доставка. В скором времени планируется открытие заведение в самом центре Казани.</p>
        </div>
        <div class="col-md-5">
            <img class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" src="image/зал.jpg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 500x500"><title>Placeholder</title><rect width="100%" height="100%" fill="#eee"></rect><text x="50%" y="50%" fill="#aaa" dy=".3em">Дизайн главного зала</text></img>
        </div>
    </div>

    <hr class="featurette-divider">

    <!-- /END THE FEATURETTES -->

</div>

<%@ include file="footer.jsp" %>
<%@include file="BootstrapScripts.jsp" %>
</body>
</html>
