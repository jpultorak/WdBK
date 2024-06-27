import random
from pprint import pprint


def generate_triplets(total_options, total_voters, total_races):
    triplets = []
    for _ in range(total_voters):

        # races[n] is vote for nth races
        triplet = [
            tuple([0] * total_races for _ in range(total_options)) for _ in range(3)
        ]

        for race_number in range(total_races):

            # Randomly select chosen option for each voter
            vote = random.randint(0, total_options - 1)

            # this option is marked on exactly two ballots
            chosen_ballots = random.sample([0, 1, 2], 2)
            triplet[chosen_ballots[0]][vote][race_number] = 1
            triplet[chosen_ballots[1]][vote][race_number] = 1

            # other options receive exactly one vote
            for option in range(total_options):
                if option != vote:
                    ballot = random.randint(0, 1)
                    triplet[ballot][option][race_number] = 1

        assert is_legal_triplet(triplet)
        triplets.append(sorted(triplet))

    return sorted(triplets)


def strips_from_triplets(triplets):
    strips = [strip for triple in triplets for strip in triple]
    random.shuffle(strips)
    return strips


def is_legal_triplet(triplet):

    # each triplet has three ballots
    if len(triplet) != 3:
        return False

    # each ballot has same ammount of options
    if len(triplet[0]) != len(triplet[1]) or len(triplet[1]) != len(triplet[2]):
        return False

    total_options = len(triplet[0])
    total_races = len(triplet[0][0])

    for race in range(total_races):
        votes = [0 for _ in range(total_options)]

        for strip in range(3):
            for option in range(total_options):
                votes[option] += triplet[strip][option][race]
        twos = votes.count(2)
        ones = votes.count(1)

        # max one candidate should receive two votes, and no candidate should receive
        # above 2, or 0 votes
        if twos > 1 or twos + ones != total_options:
            return False
    return True


if __name__ == "__main__":
    total_options = 4
    total_voters = 2
    total_races = 2
    triplets = generate_triplets(total_options, total_voters, total_races)
    # for vote in triplets:
    #     print("Vote:")
    #     pprint(vote)
    #     print("-------")
