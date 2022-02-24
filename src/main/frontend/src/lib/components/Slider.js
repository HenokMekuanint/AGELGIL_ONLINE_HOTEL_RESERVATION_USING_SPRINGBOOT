import React from 'react';
import noUiSlider from 'nouislider';
import 'nouislider/dist/nouislider.css';

class Slider extends React.Component{

	constructor(props){
		super(props);
		this.slider = null;
		this.onValueChanged = this.onValueChanged.bind(this);
	}

	createOptions(){
		return this.props.config;
	}

	setValue(values){
		this.min.value = values[0];
		this.max.value = values[1];
	}

	onValueChanged(values){
		this.setValue(values);
		this.props.onChange();
	}

	componentDidMount(){

		this.slider = document.getElementById(this.props.id);
		this.min = document.getElementById(`min${this.props.id}`)
		this.max = document.getElementById(`max${this.props.id}`)

		noUiSlider.create(
			this.slider,
			this.createOptions()
		);

		this.slider.noUiSlider.on('set', this.onValueChanged)

		this.setValue(this.createOptions().start);



	}

	render(){
		return (
			<div className="">
				<div id={this.props.id}></div>
				<input type="hidden" name={this.props.minName} id={`min${this.props.id}`} onChange={this.props.onChange}/>
				<input type="hidden" name={this.props.maxName} id={`max${this.props.id}`} onChange={this.props.onChange}/>
			</div>
		)


	}


}

export default Slider;
