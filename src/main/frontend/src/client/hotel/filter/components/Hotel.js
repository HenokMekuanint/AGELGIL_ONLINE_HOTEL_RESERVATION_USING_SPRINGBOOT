import React from "react";

import Config from "../../../Config";
import Media from "../../../../lib/network/Media";


class Hotel extends React.Component{

	constructor(props){
		super(props);
		this.hotel = props.hotel;
	}

	generateStandardDom(){

		let doms = []
		for(let i=0; i<this.hotel.standard; i++){
			doms.push(
				<i className="fas fa-star text-warning local-fs-4" key={i}></i>
			);
		}

		return doms;
	}

	render(){

		return (

			<div className="w-100 border border-primary bg-white local-br-2 rounded col-11 mx-auto overflow-hidden p-0 h-100">
				<div className="w-100">
					<img src={Media.getUrl(this.hotel.profileImage)} className="w-100" alt={`${this.hotel.name} Logo`}/>
				</div>
				<div className="p-4">
					<h3 className="font-weight-bold">{this.hotel.name}</h3>
					<span className="text-secondary"><i className="fas fa-map-marker-alt local-fs-4" aria-hidden="true"></i>{this.hotel.location.city}, Ethiopia</span>
									
					<div className="mt-4">
						{this.generateStandardDom()}
					</div>
					<div className="d-flex mt-2">
						<span className="px-3 py-2 font-weight-bold bg-success rounded text-white">{this.hotel.rating}</span>
					</div>
	
					<a href={`${Config.BACKEND_URL}/client/hotel/view/${this.hotel.id}`} className="btn btn-primary mt-5 font-weight-bold local-br-1 px-4">
						VIEW <i className="fa fa-arrow-right" aria-hidden="true"></i>
					</a>
				</div>
			</div>

		)

	}

}


export default Hotel;