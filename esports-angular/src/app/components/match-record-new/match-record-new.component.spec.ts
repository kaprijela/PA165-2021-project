import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchRecordNewComponent } from './match-record-new.component';

describe('MatchRecordNewComponent', () => {
  let component: MatchRecordNewComponent;
  let fixture: ComponentFixture<MatchRecordNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatchRecordNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchRecordNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
