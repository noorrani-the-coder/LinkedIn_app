import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployerFormComponent} from './employer';

describe('Employer', () => {
  let component: EmployerFormComponent;
  let fixture: ComponentFixture<EmployerFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployerFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployerFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
