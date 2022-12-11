import { tankMove } from './tankMove.js';
import { healerMove } from './healerMove.js';
import { casterMove } from './casterMove.js';
import { dpsMove } from './dpsMove.js';
import { disableButtons } from '../../UI/disableButtons.js';
import { user } from '../../user/user.js';
import { deathDisableButtons } from '../../UI/deathDisableButtons.js';
import { paralyzed } from '../statusAilmentCheck.js';


export const playerMove = (input) => {

    if (user.health > 0) {
        if (user.statusAilments.paralyze === true) {
            paralyzed();
            disableButtons();
        } else {
            if (user.role === 'Tank') {
                tankMove(input);
                disableButtons();
            } else if (user.role === 'Healer') {
                healerMove(input);
                disableButtons();
            } else if (user.role === 'Caster') {
                casterMove(input);
                disableButtons();
            } else if (user.role === 'DPS') {
                dpsMove(input);
                disableButtons();
            }
        }
    } else {
        deathDisableButtons();
    }
}