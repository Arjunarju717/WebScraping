$(document).ready(
		function() {
			$.getJSON("eventDetails", function(result) {
				$.each(result, function(i, field) {
					$(".data-table tbody").append(
							"<tr><td>" + field.websiteName + "</td><td>"
									+ field.eventName + "</td><td>"
									+ field.eventDate + "</td><td>"
									+ field.eventLocation + "</td></tr>");
				});
			});
			$(".eventTablefilter").keyup(function(e) {
			 var input, filter, table, tr, td, i, txtValue, id, cell;
			  id = $(this).attr('id');
			  input = document.getElementById(id);
			  if (id == "websiteNameFiltr"){
				  cell=0;  
			  } else if (id == "eventNameFiltr"){
				  cell = 1;  
			  } else if (id == "eventDateFiltr"){
				  cell = 2;  
			  } else if(id == "eventLocationFiltr"){
				  cell = 3;  
			  } 
			  filter = this.value.toUpperCase();
			  table = document.getElementById("eventTable");
			  tr = table.getElementsByTagName("tr");

			  // Loop through all table rows, and hide those who don't match the search query
			  for (i = 0; i < tr.length; i++) {
			    td = tr[i].getElementsByTagName("td")[cell];
			    if (td) {
			      txtValue = td.textContent || td.innerText;
			      if (txtValue.toUpperCase().indexOf(filter) > -1) {
			        tr[i].style.display = "";
			      } else {
			        tr[i].style.display = "none";
			      }
			    }
			  }
			});
		});
