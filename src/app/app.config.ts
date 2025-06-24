import { provideRouter } from '@angular/router';
import { importProvidersFrom } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';
import { routes } from './app.routes';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

export const appConfig = {
  providers: [
    provideRouter(routes),
    importProvidersFrom(BrowserModule, ReactiveFormsModule),
    provideHttpClient(),
    provideAnimations()
  ]
};
