import React, { forwardRef } from "react"

import { UseFormRegisterReturn } from "react-hook-form"
import { twMerge } from "tailwind-merge"
import { Label } from "./ui/label";
import { Input } from "./ui/input";

interface FormFieldProps extends UseFormRegisterReturn {
  label: string;
  type?: string;
  placeholder: string;
  errorMessage: string | undefined;
  className?: string;
  variant?: "primary" | "secondary";
  customInput?: React.ReactNode;
};

export const FormField = forwardRef<HTMLInputElement, FormFieldProps>(
  (
    {
      label,
      placeholder,
      type,
      errorMessage,
      className,
      variant = "primary",
      customInput,
      ...props
    },
    ref
  ) => {
    return (
      <div className="flex flex-col gap-1 flex-1 justify-end">
        <Label htmlFor={props.name}>{label}</Label>
        {customInput || (
          <Input
            type={type}
            placeholder={placeholder}
            className={twMerge("w-full bg-light-grey", className)}
            {...props}
            ref={ref}
          />
        )}
        {errorMessage && (
          <p  className="text-xs text-red-500 font-bold sm:text-xs">
            {errorMessage}
          </p>
        )}
      </div>
    )
  }
)