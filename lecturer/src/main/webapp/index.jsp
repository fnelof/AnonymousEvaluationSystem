<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link type="text/css" href="/lecturer/resources/css/index.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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

    <form id="lecturerGrid">
        <table class="table table-striped table-dark">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Department</th>
                <th scope="col">Syllabus</th>
                <th scope="col">Course</th>
                <th scope="col">Instructor</th>
            </tr>
            </thead>
            <tbody id="tableBody"></tbody>
        </table>
    </form>
    <div id="attendanceChain">
        <div class="form-group" id="attendanceForm">
            <textarea class="form-control" rows="5" id="chainJson"></textarea>
            <button class="btn btn-primary float-right" id="populateHashChain">Populate Lectures</button><br><br>
            <form id="attendanceHashChain"></form>
            <button class="btn btn-success float-right" id="signatureButton">Get Signatures</button><br><br>
        </div>
    </div>
    <div class="result alert alert-success" id="result"></div>
    <div id="alertBox" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    Hash chain copied to clipboard!
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary btn-block" data-dismiss="modal">OK</button>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
