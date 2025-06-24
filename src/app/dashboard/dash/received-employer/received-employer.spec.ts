import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReceivedEmployer } from './received-employer';

describe('ReceivedEmployer', () => {
  let component: ReceivedEmployer;
  let fixture: ComponentFixture<ReceivedEmployer>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReceivedEmployer]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReceivedEmployer);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
