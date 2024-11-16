import { Header } from "../components";

export default function TestPage() {
    const headerButtons: {
        to: string;
        label: string;
        color: "primary" | "secondary";
      }[] = [{ to: "/", label: "Sign Out", color: "primary" }];

    return (
        <div>
            <Header buttons={headerButtons} />
        </div>
    );
}
