import { TestBed, inject } from '@angular/core/testing';

import { AnalyticalStatementService } from './analytical-statement.service';

describe('AnalyticalStatementService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AnalyticalStatementService]
    });
  });

  it('should be created', inject([AnalyticalStatementService], (service: AnalyticalStatementService) => {
    expect(service).toBeTruthy();
  }));
});
