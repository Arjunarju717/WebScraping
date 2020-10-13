$(document).ready(
		function() {
			$.getJSON("websiteDetails", function(result) {
				$.each(result, function(i, field) {
					$(".data-table tbody").append(
							"<tr><td>"
									+ field.name + "</td><td>" + field.url
									+ "</td><td>" + field.elementType
									+ "</td><td>" + field.elementId
									+ "</td><td>" + field.elementClass
									+ "</td><td>" + field.eventNameIndex
									+ "</td><td>" + field.eventDateIndex
									+ "</td><td>" + field.eventLocationIndex
									+ "</td></tr>");
				});
			});
			$(".save-btn").click(function(e) {
				 var websiteDetailsDTO = {
						  name : $('#name').val(),
						  url : $('#url').val(),
						  elementType : $('#elementType').val(),
						  elementId : $('#elementId').val(),
						  elementClass : $('#elementClass').val(),
						  eventNameIndex : $('#eventNameIndex').val(),
						  eventDateIndex : $('#eventDateIndex').val(),
						  eventLocationIndex : $('#eventLocationIndex').val()
				        }
				 $.ajax({
				            url: '/websiteDetails',
				            type: 'post',
				            dataType: 'json',
				            contentType: 'application/json',
				            data: JSON.stringify(websiteDetailsDTO),
				            success: function (result) {
				            	window.location.reload();
				            }, error: function(error) {
				            	  alert(error.responseJSON.message);
				            }
				            
				        });

			});
		});