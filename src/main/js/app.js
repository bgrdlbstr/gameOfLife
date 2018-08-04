const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

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

ReactDOM.render(
	<App />,
	document.getElementById('react')
)