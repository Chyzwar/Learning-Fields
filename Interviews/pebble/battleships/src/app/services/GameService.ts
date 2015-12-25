/// <reference path='../../typings/_custom.d.ts' />

import {provide, Injectable} from 'angular2/angular2';

const ammo = 40;
const dimensions = { x: 10, y: 10 };
const initialFleet = [
  { type: 'Destroyer', count: 2, size: 5 },
  { type: 'BattleShip', count: 1, size: 4 }]


type Area = { isHit: boolean, hasShip: boolean };
type Point = { x: number; y: number };
type Ship = { type: string, size: number, coords: Array<Point> };

@Injectable()
export class GameService {

  grid: Area[][];
  fleet: Ship[];
  plays: Point[];
  ammo: number;
  hasWon: boolean;

  constructor() {
    this.grid = this.initGrid(
      dimensions.x,
      dimensions.y
    );

    this.fleet = this.initFleet(
      initialFleet
    );
    this.ammo = ammo;
    this.hasWon = true;
  }

  private initFleet(initialFleet: any): Ship[] {
    var fleet = [];

    initialFleet.forEach(
      formation => {
        this.positionShips(formation).forEach(
          ship => {
            fleet.push(ship);
          }
        );
      }
    );
    return fleet;
  }

  private positionShips(formation: any): Ship[] {
    var formationShips = [];

    for (var i = 0; i < formation.count; ++i) {
      formationShips.push(
        this.createShip(
          formation.type, formation.size)
      );
    }

    return formationShips;
  }

  private createShip(type: string, size: number): Ship {
    var ship = {
      type: type,
      size: size,
      coords: []
    };

    ship.coords = this.insertShip(size);

    ship.coords.forEach(
      point => {
        this.grid[point.x][point.y].hasShip = true;
      }
    );
    return ship;
  }

  private insertShip(size: number): Point[] {
    var x = this.randomNumber(0, dimensions.x - 1);
    var y = this.randomNumber(0, dimensions.y - 1);

    if (!this.hasShip(x, y)) {
      if (x + size < dimensions.x) {
        return this.attemptInsert(x, y, size, 'x');
      }
      else if (y + size < dimensions.y) {
        return this.attemptInsert(x, y, size, 'y');
      }
    }

    return this.insertShip(size);
  }

  private attemptInsert(x: number, y: number, size: number, direction: string): Point[] {
    var coords = [];
    var canPosition = true;

    coords.push({ x: x, y: y });

    for (var i = 1; i < size; ++i) {
      if (direction === 'x') {
        if (this.hasShip(x + i, y)) {
          canPosition = false;
        } else {
          coords.push({ x: x + i, y: y });
        }
      } else {
        if (this.hasShip(x, y + i)) {
          canPosition = false;
        } else {
          coords.push({ x: x, y: y + i });
        }
      }
    }

    if (canPosition) {
      return coords;
    } else {
      return this.insertShip(size);
    }
  }


  private hasShip(x: number, y: number): boolean {
    return this.grid[x][y].hasShip || false;
  }

  private randomNumber(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1) + min);
  }

  private initGrid(x: number, y: number): Area[][] {
    var matrix = [];
    for (var i: number = 0; i < x; i++) {
      matrix[i] = [];
      for (var j: number = 0; j < y; j++) {
        matrix[i][j] = { isHit: false, hasShip: false };
      }
    }
    return matrix;
  }

  static create(): GameService {
    return new GameService();
  }


  play(coord: Point) {
    this.ammo -= 1;
    this.grid[coord.x][coord.y].isHit = true;

    if (ammo === 0) {
      this.grid.forEach(
        row => {
          row.forEach(area => {
            if (area.hasShip && !area.isHit)
              this.hasWon = false;
          }
          );
        }
      );
    }
  }
}

export var GAMESERVICE_BINDINGS = [
  provide(GameService, { useClass: GameService })
];


