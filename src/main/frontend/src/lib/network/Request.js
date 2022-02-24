class Request{

	static Method = class {
		static GET = 0;
		static POST = 1;
	}

	constructor(url, method, data, header){
		this.url = url;
		this.method = method;
		this.data = data;
		
		this.header = header;
		if(typeof header == "undefined" || header == null)
			this.header = {};

		this.onSuccessCallBack = function(){}
		this.errorCallBack = function(){}
	}

	setSuccessCallBack(func){
		this.onSuccessCallBack = func;
	}

	setErrorCallBack(func){
		this.errorCallBack = func;
	}

	onSuccess(data){
		this.onSuccessCallBack(data);
	}

	onError(err){
		alert("Error Happened");
		this.errorCallBack(err);
	}

}

export default Request;