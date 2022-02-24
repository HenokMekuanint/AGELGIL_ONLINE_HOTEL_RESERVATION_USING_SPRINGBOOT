import Request from "./Request";


class ApiClient{

	constructor(baseUrl){
		this.baseUrl = baseUrl;
	}

	createRequestHeaders(request){
		let headers = request.header;
		headers['Content-Type'] = "application/json";
		return headers;
	}

	createRequestOptions(request){
		let options = {
			headers: this.createRequestHeaders(request),
		}
		if(request.method === Request.Method.POST){
			options["method"] = "POST";
			options["body"] = JSON.stringify(request.data)
		}
		else{
			options["method"] = "GET"
		}
		return options;
	}

	getUrl(request){
		let url =`${this.baseUrl}/${request.url}`
		if(request.method === Request.Method.GET)
			url += "?"+new URLSearchParams(request.data)
		return url;
	}

	call(request){
		fetch(
			this.getUrl(request),
			this.createRequestOptions(request)
		)
		.then(response => response.json())
		.then(data => request.onSuccess(data));
	}

}

export default ApiClient;