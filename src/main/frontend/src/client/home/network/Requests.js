import Request from "../../../lib/network/Request";


class GetCititesRequest extends Request{
	constructor(){
		super("home/cities/", Request.Method.GET, {}, {});
	}
}


class GetHotelsInCityRequest extends Request{

	constructor(city){
		super("hotel/filter/", Request.Method.GET, {"city": city}, {});
	}
}


export {GetCititesRequest, GetHotelsInCityRequest};