/// <reference path='../../typings/_custom.d.ts' />

import {provide, Injectable} from 'angular2/angular2';

const dimensions = {x: 10, y: 10};
const initialFleet = [
  {type: 'Destroyer', count: 2},
  {type: 'BattleShip', count: 1}]

type Area = { isHit: boolean }
type Point = { x: number; y: number };
type Ship = { type: string, size: number, coords: Array<Point>};

@Injectable()
export class GameService {

  fleet: Ship[];
  plays: Point[];
  grid: Area[][];

  constructor(){
    this.grid = this.initGrid(
        dimensions.x,
        dimensions.y
      );

    this.fleet = this.initFleet(
        initialFleet
      );

    this.plays = [];
  }

  initFleet(initialFleet): any {
    console.log('flee possitions');
  }

  initGrid(x,y) : any {
    var matrix = [];
    for (var i: number = 0; i < x; i++) {
      matrix[i] = [];
      for (var j: number = 0; j < y; j++) {
        matrix[i][j] = { isHit: false };
      }
    }
    return matrix;
  }

  static create() {
    return new GameService();
  }

  dispose() {
    this.grid = null
    this.plays = null;
  }


  play(coord: Point) {
    const { x, y } = coord;
    console.log(coord);
    this.plays.push(coord);
  }
}

export var GAMESERVICE_BINDINGS = [
  provide(GameService, { useClass: GameService })
];


