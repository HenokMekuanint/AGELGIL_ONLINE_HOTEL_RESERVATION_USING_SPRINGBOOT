import ApiClient from "../../../lib/network/ApiClient";
import Config from "../../Config";

class NetworkProvider{

	static apiClient = null;

	static getApiClient(){
		if(NetworkProvider.apiClient == null){
			NetworkProvider.apiClient = new ApiClient(Config.API_URL);
		}
		return NetworkProvider.apiClient;
	}

}

export default NetworkProvider;