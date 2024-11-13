```typescript
let widthClass = "w-[60rem]";
let heightClass = "h-[40rem]";
let name = "António Mendes";
let rightContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam nec purus ut purus"

return (
    <div className="flex flex-col min-h-screen m-10">
        <ConfigurationCard
        widthClass={widthClass}
        heightClass={heightClass}
        name={name}
        rightContent={rightContent}
    />
    </div>
)
```