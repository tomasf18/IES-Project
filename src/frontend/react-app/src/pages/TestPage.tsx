import React, { useEffect, useState } from 'react';
import { PlayersCard } from "../components";
import { Google } from "../assets";
import { Navbar } from "../components";

export default function TestPage() {
    
    return (
        <div>
            <Navbar options={['SESSIONS']} color="green" size='w-1/5'/>
            <Navbar options={['GET', 'POST', 'PUT', 'DELETE']} color="purple" size='w-1/5'/>
        </div>
    );
}
