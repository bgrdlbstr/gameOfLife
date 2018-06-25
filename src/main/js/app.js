const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

// import './App.css';
import Game from './Game';

class App extends React.Component {
		render() {
			return (
				<div className="App">
					<h1>Conway's Game of Life</h1>
					<Game />
				</div>
			);
		}
	}
export default App;

// class App extends React.Component {

// 	constructor(props) {
// 		super(props);
// 		this.state = {golGrid: {}};
// 	}

// 	componentDidMount() {
// 		client({method: 'GET', path: '/generation'}).done(response => {
// 			this.setState({golGrid: response.entity});
// 		});
// 	}

// 	render() {
// 		return (
// 			<table>
// 				<tbody>
// 					<tr>
// 						<th>Generation Number</th>
// 						<th>Life X</th>
// 						<th>Life Y</th>
// 					</tr>
//           			<LifeList lifeList={this.state.golGrid.life}/>
// 				</tbody>
// 			</table>
// 		)
// 	}
// }

// class LifeList extends React.Component{
// 	render() {
// 		if(!this.props.lifeList) {
// 			return null;
// 		}

// 		const lifeList = this.props.lifeList;
// 		lifeList.map((life) => console.log(life));

// 		var positions = lifeList.map((life) =>
// 			<GolUiPosition key={life.x + "_" + life.y} life={life}/>
// 		);
// 		return (
// 			<tr>
// 				<td>---</td>
// 				<td>
// 					<ul>{positions}</ul>
// 				</td>
				
// 			</tr>
// 		)
// 	}
// }

// class GolUiPosition extends React.Component{
// 	render() {
// 		if(!this.props.life) {
// 			return null;
// 		}

// 		return (
// 			<li>{this.props.life.x},{this.props.life.y}</li>
// 		)
// 	}
// }

ReactDOM.render(
	<App />,
	document.getElementById('react')
)