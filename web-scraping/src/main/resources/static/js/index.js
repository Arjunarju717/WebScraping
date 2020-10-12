$(document).ready(function() {
	$("#scrapeEventData").click(function(e) {
		$.ajax({
			url : '/eventDataScrapping',
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json',
			success : function(result) {
				alert(result.message);
			},
			error : function(error) {
				alert(error.message);
			}

		});

	});
});
