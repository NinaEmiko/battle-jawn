export function determineMaxExperience(level: number) {
    switch (level) {
      case 1:
        return 50;
      case 2:
        return 125;
      case 3:
        return 300;
      case 4:
        return 500;
      case 5:
        return 750;
      case 6:
        return 1250;
      case 7:
        return 2000;
      case 8:
        return 3000;
      case 9:
      case 10:
        return 5000;
      default:
        return 50;
    }
  }

  export function determineNumerator(level: number, experience: number) {
    switch (level) {
      case 1:
        return experience;
      case 2:
        return experience - 50;
      case 3:
        return experience - 125;
      case 4:
        return experience - 300;
      case 5:
        return experience - 500;
      case 6:
        return experience - 750;
      case 7:
        return experience - 1250;
      case 8:
        return experience - 2000;
      case 9:
        return experience - 3000;
      case 10:
        return experience - 5000;
      default:
        return experience - 50;
    }
  }