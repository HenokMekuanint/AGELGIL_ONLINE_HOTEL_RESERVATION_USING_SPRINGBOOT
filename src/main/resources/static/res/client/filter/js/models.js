'use strict';

let Model = require("../../../../frontend/src/lib/data/Model")

export class City extends Model{

	constructor(name, backgroundImage){
		this.name = name;
		this.backgroundImage = backgroundImage;
	}

	static fromJson(json){
		return super.fromJson(City, json);
	}

}


define([
	'require'
], function() {
	
	return City;
	
});