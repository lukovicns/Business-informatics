import { Component, OnInit } from '@angular/core';
import { fadeIn } from '../../../animations';
import { RtgsService } from '../../services/rtgs.service';

@Component({
  selector: 'app-rtgs',
  templateUrl: './rtgs.component.html',
  styleUrls: ['./rtgs.component.css'],
  animations: [fadeIn()]
})
export class RtgsComponent implements OnInit {

  rtgsList: any = [];

  constructor(private rtgsService: RtgsService) { }

  ngOnInit() {
    this.rtgsService.getRtgsSettlements()
    .subscribe(res => this.rtgsList = res);
  }
}
