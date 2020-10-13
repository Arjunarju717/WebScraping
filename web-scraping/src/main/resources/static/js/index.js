$(document).ready(function() {
	$("#scrapeEventData").click(function(e) {
		$('#loading-image').show();
		$.ajax({
			url : '/eventDataScrapping',
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json',
			success : function(result) {
				$('#loading-image').hide();
				alert(result.message);
			},
			error : function(error) {
				$('#loading-image').hide();
				alert(error.responseJSON.message);
			}
		});

	});
});
