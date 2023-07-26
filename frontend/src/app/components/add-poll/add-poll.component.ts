import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormGroup,
  Validators,
} from "@angular/forms";
import { ToastrService } from "ngx-toastr";
import { PollService } from "src/app/services/poll/poll.service";

@Component({
  selector: "app-add-poll",
  templateUrl: "./add-poll.component.html",
  styleUrls: ["./add-poll.component.css"],
})
export class AddPollComponent implements OnInit {
  pollForm!: FormGroup;

  constructor(private fb: FormBuilder, private toastr: ToastrService, private pollService: PollService ) {}

  ngOnInit(): void {
    this.pollForm = this.fb.group({
      title: ["", Validators.required],
      endDate: [
        "",
        Validators.compose([
          Validators.required,
          this.endDateValidator, // Custom validator
        ]),
      ],
      options: this.fb.array([], Validators.minLength(2)),
    });
  }

  endDateValidator(control: AbstractControl) {
    const endDate = new Date(control.value);
    const currentTime = new Date();
    const minimumEndDate = new Date(currentTime.getTime() + 60 * 60 * 1000);
    // Current time + 1 hour
    return endDate > minimumEndDate ? null : { endDateInvalid: true };
  }

  get options() {
    return this.pollForm.get("options") as FormArray;
  }

  addOption() {
    this.options.push(this.fb.control("", Validators.required));
  }

  removeOption(index: number) {
    this.options.removeAt(index);
  }
  todayISOString(): string {
    return new Date().toISOString().slice(0, 16); //make default date the current date
  }

  onSubmit() {
    if (this.pollForm.valid) {

      console.log(this.pollForm.value);
      this.pollService.addPoll(this.pollForm.value).subscribe(
        (res: any) => {
          this.toastr.success(res);
        },
        (error: HttpErrorResponse) => {
          this.toastr.error(error.message);
        }
      );

      this.pollForm.reset();
      this.options.clear();
      
    } else {
      this.toastr.error(
        "Make sure you have added at least 2 options to create a poll OR the time should be at least an hour more than the current time"
      );
    }
  }
}
