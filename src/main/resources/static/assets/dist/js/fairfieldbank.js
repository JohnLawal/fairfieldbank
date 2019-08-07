$(function() {
	$('#tblStudents').DataTable({
		"ordering" : false
	});
	
});

function deleteStudent(studentId) {

	$.confirm({
		title : 'Are you sure you want to delete this student?',
		content : "This action cannot be reversed",
		buttons : {
			cancel : {
				text : 'Cancel',
				btnClass : 'btn btn-default'
			},
			confirm : {
				text : "Delete",
				btnClass : 'btn btn-warning',
				action : function() {
					location.href = "/eregistrar/students/delete/" + studentId;
				}
			}
		}
	});
}
