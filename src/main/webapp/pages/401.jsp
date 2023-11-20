<%@ page isErrorPage="true" %>

<!doctype html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./static/css/main.css" rel="stylesheet">
        <link href="./static/css/custom.css" rel="stylesheet">
        <title>401</title>
    </head>

    <body class="h-screen sm:flex sm:flex-row">
        <%@include file="nav.jsp" %>

        <div class="sm:w-[80%] flex flex-row items-center justify-center h-full">
            <div class="text-center">
                <p class="text-2xl text-blue-400">404</p>
                <p>
                    ${message}
                </p>

            </div>
        </div>
    </body>

</html>