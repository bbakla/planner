//weekly plan creation title function
window.onload = function() {
	var date = new Date();
	var onejan = new Date(date.getFullYear(), 0, 1);
	var weekNumber = Math.ceil((((date - onejan) / 86400000) + onejan.getDay() + 1) / 7);

	var d1 = new Date(date);
	var index = d1.getDay();

	if (index == 0) {
		date.setDate(date.getDate() - 6);
		d1.setDate(d1.getDate() - 2);

	} else if (index == 1) {
		date.setDate(date.getDate());
		d1.setDate(d1.getDate() + 4);

	} else if (index != 1 && index > 0) {
		date.setDate(date.getDate() - (index - 1));
		d1.setDate(d1.getDate() + (index + 3));
	}

	var startDate = date.getDate() + "." + (date.getMonth() + 1) + "." + date.getYear();
	var endDate = d1.getDate() + "." + (d1.getMonth() + 1) + "." + d1.getYear();

	var goalTitle = "Goals of " + weekNumber + ". week (" + startDate + " - " + endDate + ")";

	$('#goalTitle')[0].innerHTML = goalTitle;
}