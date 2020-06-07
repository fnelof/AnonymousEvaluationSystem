<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link type="text/css" href="/lecturer/resources/css/index.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="/lecturer/resources/js/index.js"></script>
    <title>
        Lecturer
    </title>
</head>
<body>
<div class="container">
    <h2>Lecturer</h2>
    <form id="form">
        <div class="row form-group">
            <label for="instructor" class="col-sm-2">Instructor</label>
            <input type="text" id="instructor" class="form-control col-sm-10" placeholder="Instructor"/>
        </div>
        <div class="row form-group">
            <label for="course" class="col-sm-2">Course</label>
            <input class="form-control col-sm-10" type="text" id="course" placeholder="Course"/>
        </div>
        <div class="row">
            <div class="col-sm-8"></div>
            <button class="btn btn-default col-sm-2" type="button" role="reset">Cancel</button>
            <button class="btn btn-success col-sm-2" type="submit" role="submit">Filter</button>
        </div>
    </form>
</div>

</body>
</html>