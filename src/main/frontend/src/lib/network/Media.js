import Config from "../../client/Config";

class Media{

	// static getUrl(file){
	// 	return `${Config.MEDIA_URL}/${file}`;
	// }

	static getUrl(file){
		return file;
	}

}

export default Media;