const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

const CELL_SIZE = 20;
const WIDTH = 800;
const HEIGHT = 600;

class Cell extends React.Component {
  render() {
    const { x, y } = this.props;
    return (
        <div className="Cell" style={{
            left: `${CELL_SIZE * x + 1}px`,
            top: `${CELL_SIZE * y + 1}px`,
            width: `${CELL_SIZE - 1}px`,
            height: `${CELL_SIZE - 1}px`,
        }} />
    );
  }
}

class Game extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      cells: [],
      interval: 1000,
      isRunning: false
    };

    this.rows = HEIGHT / CELL_SIZE;
    this.cols = WIDTH / CELL_SIZE;
    this.board = this.makeEmptyBoard();

    this.runGame = this.runGame.bind(this);
    this.stopGame = this.stopGame.bind(this);
    this.handleIntervalChange = this.handleIntervalChange.bind(this);
    this.updateGrid = this.updateGrid.bind(this);
    this.handleClear = this.handleClear.bind(this);
  }

  runGame() {
    console.log("runGame")
    this.setState({ isRunning: true });
    this.runIteration();
  }

  stopGame() {
    console.log("stopGame")
      this.setState({ isRunning: false });
      if (this.timeoutHandler) {
          window.clearTimeout(this.timeoutHandler);
          this.timeoutHandler = null;
      }
  }

  handleIntervalChange(event) {
    console.log("handleIntervalChange")
    this.setState({ interval: event.target.value });
  }
  
  handleClear() {
    // this.board = this.makeEmptyBoard();
    // this.setState({ cells: this.makeCells() });
    console.log("Running handleClear")
    client({method: 'GET', path: '/reset'}).done(response => {
      this.setState(
        {golGrid: response.entity},
        () => this.updateGrid(false)
      );
    });
  }

  updateGrid(shouldContinue) {
    console.log("updateGrid")
    if(!this.state.golGrid || !this.state.golGrid.life) {
      console.log("updateGrid - noData")
      return;
    }

    let newBoard = this.makeEmptyBoard();
    
    this.state.golGrid.life.map(
      (l) => {
        console.log("life at x=" + l.x + " y=" + l.y)
        newBoard[l.y][l.x] = true;
      }
    );
    
    this.board = newBoard;
    this.setState({ cells: this.makeCells() });
    
    if(shouldContinue) {
      this.timeoutHandler = window.setTimeout(() => {
        this.runIteration();
      }, this.state.interval);
    }
  }
  
  runIteration() {
    console.log("Running iteration")
    client({method: 'GET', path: '/generation'}).done(response => {
      this.setState(
        {golGrid: response.entity},
        () => this.updateGrid(true)
      );
    });
  }

  // Create an empty board
  makeEmptyBoard() {
    console.log("makeEmptyBoard")
    let board = [];
    for (let y = 0; y < this.rows; y++) {
      board[y] = [];
      for (let x = 0; x < this.cols; x++) {
        board[y][x] = false;
      }
    }
    return board;
  }
  
  // Create cells from this.board
  makeCells() {
    console.log("makeCells")
    let cells = [];
    for (let y = 0; y < this.rows; y++) {
      for (let x = 0; x < this.cols; x++) {
        if (this.board[y][x]) {
          cells.push({ x, y });
        }
      }
    }
    return cells;
  }

  render() {
    const { cells } = this.state;

    return (
      <div>
        <div className="Board"
          style={{
            width: WIDTH, height: HEIGHT,
            backgroundSize: `${CELL_SIZE}px ${CELL_SIZE}px`
          }}>
          {cells.map(cell => (
            <Cell x={cell.x} y={cell.y}
                key={`${cell.x},${cell.y}`}/>
          ))}
        </div>

        <div className="controls">
          Update every <input value={this.state.interval} onChange={this.handleIntervalChange} /> ms
          {this.state.isRunning ?
            <button className="button" onClick={this.stopGame}>Stop</button> :
            <button className="button" onClick={this.runGame}>Run</button>
          }
        <button className="button" onClick={this.handleClear}>Clear</button>
        </div>

      </div>
    );
  }
}

export default Game;