import { useState } from "react";
import { TextInput, Label } from "flowbite-react";

interface DynamicFormProps {
    title: string;
    fields: Array<{ label: string; name: string }>;
    buttonLabel: string;
    checkboxLabel: string;
    onSubmit: (data: Record<string, string | boolean>) => void;
}

export default function SessionMatchInfo({
    title,
    fields,
    buttonLabel,
    checkboxLabel,
    onSubmit,
}: DynamicFormProps) {
    const [formData, setFormData] = useState<Record<string, string | boolean>>(
        {}
    );
    const [selectAll, setSelectAll] = useState(false);

    const handleChange = (name: string, value: string) => {
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleCheckboxChange = () => {
        setSelectAll((prev) => !prev);
        setFormData((prev) => ({ ...prev, selectAll: !selectAll }));
    };

    const handleSubmit = () => {
        onSubmit(formData);
    };

    return (
        <div className="p-5">
            <h3 className="text-2xl font-bold mb-5">{title}</h3>

            {fields.map((field, index) => (
                <div key={index} className="mb-4 w-1/6">
                    <Label className="mb-1 block" value={field.label} />
                    <TextInput
                        name={field.name}
                        type="text"
                        onChange={(e) =>
                            handleChange(field.name, e.target.value)
                        }
                        required
                    />
                </div>
            ))}

            <div className="w-1/6 flex items-center justify-center">
                <div>
                    <input
                        type="checkbox"
                        checked={selectAll}
                        onChange={handleCheckboxChange}
                        className="mr-2 rounded-sm"
                    />
                    <label>{checkboxLabel}</label>
                </div>
                <button
                    onClick={handleSubmit}
                    className="ml-12 bg-green-200 text-black text-lg rounded-full px-6 py-1 hover:bg-green-400 transition-colors duration-200 hover:ring-2 ring-green-100"
                >
                    {buttonLabel}
                </button>
            </div>

        </div>
    );
}
