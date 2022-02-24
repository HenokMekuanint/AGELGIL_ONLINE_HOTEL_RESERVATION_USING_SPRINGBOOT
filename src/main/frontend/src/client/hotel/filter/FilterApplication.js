import React from "react";
import ClientApplication from "../../core/ClientApplication";
import NetworkProvider from "../../core/di/NetworkProvider";
import { FilterHotels, GetCititesRequest, GetPriceRangeRequest, GetServiceTypesRequest } from "./network/Requests";
import wNumb from 'wnumb';
import Slider from "../../../lib/components/Slider";
import Hotel from "./components/Hotel";

var apiClient = NetworkProvider.getApiClient();


class FilterForm extends React.Component{

	constructor(props) {
		super(props);
		this.changeCallback = props.changeCallBack;
		this.initialValues = props.initialValues;
		this.state = {
			cities: [],
			roomPricesRange: null,
			services: null
		}
		this.setCities = this.setCities.bind(this);
		this.setRoomPricesRange = this.setRoomPricesRange.bind(this);
		this.setServices = this.setServices.bind(this);
	}

	setCities(cities){
		this.setState({cities: cities});
	}

	getCities(){
		return this.state.cities;
	}

	setRoomPricesRange(range){
		this.setState({roomPricesRange: range});
	}

	getRoomPricesRange(){
		return this.state.roomPricesRange;
	}

	setServices(services){
		this.setState({services: services});
	}

	getServices(){
		return this.state.services;
	}

	fetchCities(){
		let request = new GetCititesRequest();
		request.setSuccessCallBack(this.setCities);
		apiClient.call(request);
	}

	fetchRoomPricesRange(){
		let request = new GetPriceRangeRequest();
		request.setSuccessCallBack(this.setRoomPricesRange);
		apiClient.call(request);
	}

	fetchServices(){
		let request = new GetServiceTypesRequest();
		request.setSuccessCallBack(this.setServices);
		apiClient.call(request);
	}

	getInitialValue(field){

		let value = this.initialValues[field]

		if(typeof value === "undefined")
			return null;
		return value;
	}

	getFormValues(){
		return new FormData(document.getElementById("filterForm"));
	}

	componentDidMount(){
		this.fetchCities();
		this.fetchRoomPricesRange();
		this.fetchServices();
	}


	getStandardSliderRange(){
		let range = {min: 1, max: 6};
		const step = 100/5;
		for(let i=1; i<5; i++){
			range[`${step*i}%`] = i+1
		}
		return range;
	}

	render(){

		
		return (

			<form method="GET" action="" id="filterForm" className="px-5">

				<h2 className="text-center fw-bold">Filters</h2>
				
				<div className="my-5">
					<h3 className="fw-bold">Basic</h3>
					
					<label className="mt-4 d-block">
						<span className="d-block form-label fw-bold">Name</span>
						<input type="text" name="name" defaultValue={this.getInitialValue("name")} onChange={this.changeCallback} className="border border-primary form-control"  />
					</label>

					<label className="mt-4 d-block">
						<span className="d-block form-label fw-bold">City</span>
						<select name="city" className="form-control form-select" onChange={this.changeCallback} defaultValue={this.getInitialValue("city")}>
							<option value="">All</option>
							{
								this.getCities().map((city, index) => {
									return (this.getInitialValue("city") != null && this.getInitialValue("city").toLowerCase() === city.name.toLowerCase()) ?
									
									(<option value={city.name} key={city.name} selected>{city.name}</option>) :

									(<option value={city.name} key={city.name}>{city.name}</option>)
								})
							}
						</select>
					</label>

				</div>
				<hr/>
				<div className="local-	my-6">

					<h3 className="fw-bold">Rating</h3>

					<label className="mt-5 d-block">
						<span className="d-block form-label fw-bold mb-5">Class(Stars)</span>
						<Slider onChange={this.changeCallback} id="standard" minName="minStandard" maxName="maxStandard" config={{start: [1, 6], connect: true, range: this.getStandardSliderRange(), format: wNumb({decimals: 0}), tooltips:[wNumb({decimals:0}), wNumb({decimals: 0})], pips: {mode: 'range', density: 100/6}}}/>
					</label>

					<label className="local-mt-6 pt-4 d-block">
						<span className="d-block form-label fw-bold mb-5">User Ratings</span>
						<Slider onChange={this.changeCallback} id="ratings" minName="minUserRatings" maxName="maxUserRatings" config={{start: [0, 10], connect: true, range: {min:0, max:10}, format: wNumb({decimals: 1}), tooltips:[wNumb({decimals:1}), wNumb({decimals: 1})], pips: {mode: 'range', density: 10}}}/>
					</label>

				</div>
				<hr/>
				<div className="local-my-6">

					<h3 className="fw-bold">Budget</h3>

					<label className="mt-5 d-block">
						<span className="d-block form-label fw-bold mb-5">Room Price / Night</span>
						
						{
							this.getRoomPricesRange() == null ? 
							(
								<div></div>
							): 
							
							(
								<Slider 
									onChange={this.changeCallback} 
									id="roomPrice" 
									minName="minRoomPrice" 
									maxName="maxRoomPrice" 
									config={
										{
											start: [this.getRoomPricesRange().min, this.getRoomPricesRange().max], 
											connect: true, 
											range: this.getRoomPricesRange(), 
											format: wNumb({decimals: 0}), 
											tooltips: [
												wNumb({decimals: 0, prefix: 'ETB ', thousand: ","}), 
												wNumb({decimals: 0, prefix: 'ETB ', thousand: ","})
											]
										}}/>
							)
						}
						
						
					</label>
				</div>
				<hr/>
				<div className="my-5">
					<h3 className="fw-bold">Date</h3>
					
					<label className="mt-4 d-block">
						<span className="d-block form-label fw-bold">Check In</span>

						<div className="input-group">
								<span className="input-group-text bg-light border-right-0"><i className="fas fa-calendar text-secondary"></i></span>
								<input type="date" name="checkIn" onChange={this.changeCallback} className="form-control"  />
						</div>

					</label>

					<label className="mt-4 d-block">
						<span className="d-block form-label fw-bold">Check Out</span>

						<div className="input-group">
								<span className="input-group-text bg-light border-right-0"><i className="fas fa-calendar text-secondary"></i></span>
								<input type="date" name="checkOut" onChange={this.changeCallback} className="form-control"  />
						</div>

					</label>

				</div>
				<hr/>
				<div className="my-5">
					<h3 className="fw-bold">Services</h3>
					
					{
						this.getServices() == null ?
						(<div></div>) :
						(
							this.getServices().map((service, index) => {

								return (
									<label className="my-3 d-block form-check" key={service.name}>
										<input type="checkbox" name="services" onChange={this.changeCallback} className="form-check-input" value={service.name}/>
										<span className="form-check-label">{service.name}</span>
									</label>
								)

							})
						)
					}

					
				</div>
			</form>

		)

	}

}


class FilterApplication extends ClientApplication{

	constructor(props){
		super(props);
		this.state = {
			filterResults: null
		};
		this.onFormChange = this.onFormChange.bind(this);
		this.setFilterResults = this.setFilterResults.bind(this);
		this.fetchFilterResults = this.fetchFilterResults.bind(this);
		this.form = null;
	}

	setFilterResults(results){
		this.setState({filterResults: results});
	}

	getFilterResults(){
		return this.state.filterResults;
	}

	getInitialValues(){
		let searchParams = new URLSearchParams(window.location.search);

		let values = {}

		let fieldsIterator = searchParams.keys();

		let field = fieldsIterator.next();
		while(!field.done){
			values[field.value] = searchParams.get(field.value);
			field = fieldsIterator.next();
		}

		return values;		
	}

	fetchFilterResults(){
		let params = new URLSearchParams(this.form.getFormValues());
		let request = new FilterHotels(params);
		request.setSuccessCallBack(this.setFilterResults);
		apiClient.call(request);
	}

	onFormChange(){
		this.setFilterResults(null);
		this.fetchFilterResults();
	}

	componentDidMount(){
		super.componentDidMount();
		setTimeout(this.fetchFilterResults, 1000);
	}

	render(){

		return (
			<div className="row">
				
				<div className="col-3 border-right border-dark d-lg-block d-none">
					<FilterForm ref={instance => {this.form = instance}} initialValues={this.getInitialValues()} changeCallBack={this.onFormChange}/>
				</div>
				
				<div className="col-lg-9 col-12 p-5">
					<h2 className="font-weight-bold">Results</h2>
					<div className="row local-mt-6">
						

						{
							(this.getFilterResults() == null) ?
							(<h4 className="fw-bold">Loading...</h4>) :
							
							(this.getFilterResults().length === 0) ?
							(<h4 className="fw-bold">Oops! We couldn't find any matches.</h4>) :
							(this.getFilterResults().map((hotel, index) => {
								return (
									<div className="col-lg-4 col-12 my-3" key={index}>
										<Hotel hotel={hotel}/>
									</div>
								);
							}))
						}
					</div>
				</div>

			</div>
		)

	}

}


export default FilterApplication;
