```tsx
import { SessionMatchInfo } from "../components";

export default function TestPage() {
    // const user = useUser();
    const handleFormSubmit = (data: Record<string, string | boolean>) => {
        console.log("Form Data:", data);
    };

    return (
        <div>
            <SessionMatchInfo
                title="Session Info"
                fields={[{ label: "Session Name", name: "sessionName" }]}
                buttonLabel="Start"
                checkboxLabel="Select all players"
                onSubmit={handleFormSubmit}
            />

            <SessionMatchInfo
                title="Match Info"
                fields={[
                    { label: "Session Name", name: "sessionName" },
                    { label: "Opponent Team", name: "opponentTeam" },
                    {
                        label: "Type (Friendly or Tournament Match)",
                        name: "matchType",
                    },
                    { label: "Location", name: "location" },
                    { label: "Weather", name: "weather" },
                ]}
                buttonLabel="Start"
                checkboxLabel="Select all players"
                onSubmit={handleFormSubmit}
            />
        </div>
    );
}
```