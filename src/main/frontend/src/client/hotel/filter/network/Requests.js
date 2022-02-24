import Request from "../../../../lib/network/Request";


class GetCititesRequest extends Request{
	constructor(){
		super("hotel/cities/", Request.Method.GET, {}, {});
	}
}

class GetPriceRangeRequest extends Request{
	constructor(){
		super("hotel/service/room/price_range", Request.Method.GET, {}, {})
	}
}

class GetServiceTypesRequest extends Request{
	constructor(){
		super("hotel/servicetypes", Request.Method.GET, {}, {})
	}
}

class FilterHotels extends Request{
	constructor(filter){
		super("hotel/filter", Request.Method.GET, filter, {});
	}
}

export {GetCititesRequest, GetPriceRangeRequest, GetServiceTypesRequest, FilterHotels};