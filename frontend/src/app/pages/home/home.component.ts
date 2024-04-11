import {Component, ViewChild} from '@angular/core';
import { AnimationType } from './carousel/carousel.animations';
import { CarouselComponent } from './carousel/carousel.component';
import { Slide } from './carousel/carousel.interface';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  @ViewChild(CarouselComponent) carousel: CarouselComponent;

  animationType = AnimationType.Scale;

  slides: Slide[] = [

  ];

  constructor() {}

  setAnimationType(type: { value: AnimationType; }) {
    this.animationType = type.value;
    setTimeout(() => {
      this.carousel.onNextClick();
    });
  }
}
