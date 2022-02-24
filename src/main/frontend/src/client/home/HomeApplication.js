import React from "react";
import ClientApplication from "../core/ClientApplication";
import NetworkProvider from "../core/di/NetworkProvider";
import Hotel from "../hotel/filter/components/Hotel";
import { GetCititesRequest, GetHotelsInCityRequest } from "./network/Requests";


var apiClient = NetworkProvider.getApiClient();


class Location extends React.Component{

	constructor(props){
		super(props)
		this.city = props.city
		this.select = this.select.bind(this);
		this.setIsSelected = this.setIsSelected.bind(this);
		this.state = {
			selected: props.selected
		};
	}

	setIsSelected(selected){
		this.setState({selected: selected});
	}

	getIsSelected(){
		return this.state.selected;
	}

	select(){
		this.setIsSelected(true);
	}

	render(){
		
		return (
			<div onClick={this.props.onClick} className={`col-12 d-flex mx-auto ${this.getIsSelected()? "col-lg-5" : "col-lg-3 px-4 local-py-lg-6 my-lg-0 my-4"}`} style={{transition: "all 0.3s ease-out"}}>
				<button className={`col-12 mx-auto d-flex border-0 ${this.getIsSelected()? "local-pt-8 pb-2 px-5" : "local-pt-7 pb-4"}`}  style={{backgroundImage: `url(${this.city.image})`, backgroundSize: "150%", backgroundRepeat: "no-repeat", boxShadow: "0 0 1rem rgba(0, 0, 0, 0.8)"}}>
					<h3 className={`ml-auto text-light ${this.getIsSelected()? "fw-bold": ""}`}>{this.city.name}</h3>
				</button>
			</div>
		);
	}
	
}


class HotelsContainer extends React.Component{

	constructor(props){
		super(props);
		this.state = {
			city: props.city,
			hotels: [] 
		}

		this.setHotels = this.setHotels.bind(this);
		this.fetchHotels = this.fetchHotels.bind(this);

	}

	setCity(city){
		this.setState({city: city, hotels: []}, this.fetchHotels);

	}

	getCity(){
		return this.state.city;
	}

	setHotels(hotels){
		this.setState({hotels: hotels});
	}

	getHotels(){
		return this.state.hotels;
	}

	fetchHotels(){
		let request = new GetHotelsInCityRequest(this.getCity().name);
		request.setSuccessCallBack(this.setHotels);
		
		apiClient.call(request);
	}

	componentDidMount(){
		this.fetchHotels();
		
	}

	render(){

		return (
			<div className="local-mt-7">

				<h3 className="fw-bold px-4">Hotels in {this.getCity().name}</h3>

				<div className="row mt-5">

					{this.getHotels().map(
						(hotel, index) => {

							return (
								<div className="col-3 px-5" key={index}>
									<Hotel hotel={hotel}/>
								</div>
							)

						}
					)}

					

				</div>
			</div>

		);
	}



	


}


class LocationsContainer extends React.Component{

	constructor(props){
		super(props)
		this.state = {
			cities: [],
			selectedCityIndex: 0
		}
		this.setCitites = this.setCitites.bind(this);
		this.getCities = this.getCities.bind(this);
		this.selectCity = this.selectCity.bind(this);
		this.locations = []
		this.hotelsContainer = null;
	}

	setCitites(cities){
		this.setState({cities: cities, selectedCityIndex: 0});
		this.childDefs = []
	}

	getCities(){
		return this.state.cities;
	}

	selectCity(index){
		this.locations[this.getSelectedCityIndex()].setIsSelected(false);
		this.locations[index].setIsSelected(true);
		this.hotelsContainer.setCity(this.getCities()[index]);
		this.setState({selectedCityIndex: index});

	}

	getSelectedCityIndex(){
		return this.state.selectedCityIndex;
	}

	componentDidMount(){
		let request = new GetCititesRequest();
		request.setSuccessCallBack(this.setCitites)
		apiClient.call(
			request
		);
	}

	generateLocationDoms(){
		let doms = [];
		let cities = this.getCities();
		this.locations = [];
		for(let i=0; i<cities.length; i++){
			doms.push(<Location ref={instance => this.locations[i] = instance} onClick={() => this.selectCity(i)} city={cities[i]} key={cities[i].name} selected={ i=== this.getSelectedCityIndex()}></Location>)
		}

		return doms;
	}

	getDoms(){
		return this.doms
	}

	render(){

		return (
			<div className="">
				
				<div className="">
					<h2 className="fw-bold px-4">Locations to Visit in Ethiopia</h2>
					<div className="row mt-5 p-0">
						{this.generateLocationDoms()}
					</div>
				</div>

				{this.getCities().length > 0 &&
					
					<HotelsContainer ref={instance => {this.hotelsContainer = instance}} city={this.getCities()[this.getSelectedCityIndex()]}/>
				}

			</div>

			
		)

	}


}


class HomeApplication extends ClientApplication{
	
	componentDidMount(){
		super.componentDidMount()
	}

	render(){
		return <LocationsContainer/>
	}

}


export default HomeApplication;
