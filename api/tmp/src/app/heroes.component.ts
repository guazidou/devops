import { Component, OnInit } from '@angular/core';
import { Hero } from './hero';
import { HeroDetailComponent } from './hero-detail.component';
import { HeroService } from './hero.service';
import { Router } from "@angular/router";

@Component({
    selector: 'my-heroes',
    templateUrl: 'src/app/heroes.component.html',
    styleUrls: ['src/app/heroes.component.css'],
    directives: [HeroDetailComponent],
    providers: []
})
export class HeroesComponent  implements OnInit {
    title = 'Tour of Heroes';
    heroes: Hero[];
    selectedHero: Hero;
    constructor(
        private router: Router,
        private heroService: HeroService) { }

    onSelect(hero: Hero) { this.selectedHero = hero; }

    private getHeroes() {
        this.heroService.getHeroes().then(heroes => this.heroes = heroes);
    }

    ngOnInit() {
        this.getHeroes();
    }

    gotoDetail() {
        this.router.navigate(['/detail', this.selectedHero.id]);
    }
}