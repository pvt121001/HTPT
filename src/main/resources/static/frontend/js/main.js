let round_trip = document.getElementById("round_trip");
let one_way = document.getElementById("one_way");
let multi_city = document.getElementById("multi_city");
let formDate = document.getElementById("formDate");
let returnDate = document.getElementById("returnDate");
let travel = document.getElementById("travel");
let searchFilterBtn = document.getElementById("searchFilterBtn");
let convertBox = document.getElementById("convertBox");
let convertBtn = document.getElementById("convertBtn");
let multipleField = document.getElementById("multipleField");
let actionBtn = document.getElementById("actionBtn");
let allClearBtn = document.getElementById("allClearBtn");
let searchFlightBtn = document.getElementById("searchFlightBtn");
let secondSearchFlightBtn = document.getElementById("secondSearchFlightBtn");

function getFlightType() {
	round_trip.checked = true;
}
getFlightType()

function roundTrip() {
	if (round_trip.checked === true) {
		convertBox.classList.add("position-relative");
		convertBtn.classList.remove("d-none");
		formDate.classList.add("col-md-3");
		formDate.classList.remove("col-md-4");
		travel.classList.add("col-md-3");
		travel.classList.remove("col-md-4", "mb-md-0", "mb-3");
		searchFilterBtn.classList.add("col-md-3");
		searchFilterBtn.classList.remove("col-md-4");
		returnDate.classList.remove("d-none");
		multipleField.classList.add("d-none");
		actionBtn.classList.add("d-none");
		actionBtn.classList.remove("d-md-flex", "d-block");
		searchFlightBtn.classList.remove("d-lg-block", "d-none");
		secondSearchFlightBtn.classList.add("d-none");
		secondSearchFlightBtn.classList.remove("d-lg-none");
	}
}

round_trip.addEventListener("click", roundTrip);


function oneWay() {
	if (one_way.checked === true) {
		convertBox.classList.add("position-relative");
		convertBtn.classList.remove("d-none");
		formDate.classList.remove("col-md-3");
		formDate.classList.add("col-md-4");
		travel.classList.remove("col-md-3");
		travel.classList.add("col-md-4", "mb-md-0", "mb-3");
		searchFilterBtn.classList.remove("col-md-3");
		searchFilterBtn.classList.add("col-md-4");
		returnDate.classList.add("d-none");
		multipleField.classList.add("d-none");
		actionBtn.classList.add("d-none");
		actionBtn.classList.remove("d-md-flex d-block");
		searchFlightBtn.classList.remove("d-lg-block", "d-none");
		secondSearchFlightBtn.classList.add("d-none");
		secondSearchFlightBtn.classList.remove("d-lg-none");
	}
}

one_way.addEventListener("click", oneWay);


function multiCity() {
	if (multi_city.checked === true) {
		convertBox.classList.remove("position-relative");
		convertBtn.classList.add("d-none");
		formDate.classList.remove("col-md-3");
		formDate.classList.add("col-md-4");
		travel.classList.remove("col-md-3");
		travel.classList.add("col-md-4", "mb-md-0", "mb-3");
		searchFilterBtn.classList.remove("col-md-3");
		searchFilterBtn.classList.add("col-md-4");
		returnDate.classList.add("d-none");
		multipleField.classList.remove("d-none");
		actionBtn.classList.remove("d-none");
		actionBtn.classList.add("text-center");
		searchFlightBtn.classList.add("d-lg-block", "d-none");
		secondSearchFlightBtn.classList.remove("d-none");
		secondSearchFlightBtn.classList.add("d-lg-none");
	}
}

multi_city.addEventListener("click", multiCity);



let multipleFieldShow = $("#multipleField");
let addFlight = $("#add_flight");
let id = 0;
$(addFlight).on("click", function () {
	id++;
	if (id < 4) {
		let row = `<div class="row mt-2 formId z-n1" id="ids${id}">
	<div class="col-xl-5 col-sm-12 d-sm-flex">
	<div class="input-group ">
	<span class="input-group-text ms-1 bg-light ps-3 pe-0" id="basic-addon1">
	<i class="ri-map-pin-fill fs-4 text-secondary"></i>
	</span>
	<input type="text" class="form-control py-xl-2 py-4 rounded-end border-start-0 text-muted fs-5 fw-semibold focus-ring z-0 bg-light" placeholder="From Where?"  style="--bs-focus-ring-color: rgba(var(--bs-seconda-rgb), 0)">
	</div>
	<div class="input-group mt-sm-0 mt-1">
	<span class="input-group-text ms-1 bg-light ps-4 pe-0" id="basic-addon1">
	<i class="ri-map-pin-fill fs-4 text-secondary "></i>
	</span>
	<input type="text" class="form-control py-xl-2 py-4 rounded-end focus-ring border-start-0 text-muted fs-5 fw-semibold bg-light"  style="--bs-focus-ring-color: rgba(var(--bs-seconda-rgb), 0)" placeholder="To Where?">
	</div>
	</div>
	<div class="col-xl-7 col-sm-12 ps-xl-0 ps-3 mt-xl-0 mt-3">
	<div class="row">
	<div id="formDate" class="col-md-4 col-sm-6 col-12">
	<div class="bg-light border border-1 border-secondary rounded px-2 d-flex align-items-center py-2 w-100">
	<span class="ps-1 pe-3 fs-4"><i class="ri-calendar-todo-fill"></i></span>
	<div class="">
	<span class="text-dark fs-6 fw-bold">Oct 20</span><br>
	<span class="text-muted">2023</span>
	</div>
	</div>
	</div>
	<div onclick="formRemove('ids${id}')" class="col-md-4 col-sm-6 col-12 d-flex align-items-center">
	<button type="button" class="text-capitalize border-0 bg-transparent text-danger fs-6 fw-semibold">
	<i class="ri-delete-bin-6-line"></i> clear</button>
	</div>
	</div>
	</div>
	</div>`;
		multipleFieldShow.append(row);

	}
})

function formRemove(id) {
	$("#" + id).remove();
}

function allClear() {
	let formId = $(".formId");
	formId.remove();
}



