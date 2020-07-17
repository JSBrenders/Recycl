<nav class="navbar navbar-expand-lg navbar-light bg-success fixed-top">
<%--    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Menu</a>--%>
<%--    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--        <span class="navbar-toggler-icon"></span>--%>
<%--    </button>--%>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link ml-3" href="${pageContext.request.contextPath}/">Accueil<span class="sr-only">(current)</span></a>
            </li>
        </ul>

        <span id="Paris" class="mx-3 h3 text-light">Paris</span>
        <label class="switch">
            <input id="check" type="checkbox">
            <span class="slider round"></span>
        </label>
        <span id="Lille" class="mx-3 h3 text-dark">Lille</span>

    </div>

</nav>


