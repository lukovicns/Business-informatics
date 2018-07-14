import { Component, OnInit } from '@angular/core';
import { fadeIn } from '../../../animations';
import { ClearingService } from '../../services/clearing.service';

@Component({
  selector: 'app-clearing',
  templateUrl: './clearing.component.html',
  styleUrls: ['./clearing.component.css'],
  animations: [fadeIn()]
})
export class ClearingComponent implements OnInit {

  clearings: any = [];

  constructor(private clearingService: ClearingService) { }

  ngOnInit() {
    this.clearingService.getClearingSettlements()
    .subscribe(res => this.clearings = res);
  }
}
