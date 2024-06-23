import random
from pprint import pprint


def generate_triplets(total_options, total_voters):
    triplets = []
    for _ in range(total_voters):

        # Randomly select chosen option for each voter
        vote = random.randint(0, total_options - 1)
        triplet = [[0] * total_options for _ in range(3)]

        # this option is marked on exactly two ballots
        chosen_ballots = random.sample([0, 1, 2], 2)
        triplet[chosen_ballots[0]][vote] = 1
        triplet[chosen_ballots[1]][vote] = 1

        # other options receive exactly one vote
        for option in range(total_options):
            if option != vote:
                ballot = random.randint(0, 1)
                triplet[ballot][option] = 1

        assert is_legal_triplet(triplet)
        triplets.append(sorted(triplet))

    assert len(triplets) == total_voters
    return sorted(triplets)


def strips_from_triplets(triplets):
    strips = [strip for triple in triplets for strip in triple]
    random.shuffle(strips)
    return strips


def is_legal_triplet(triplet):
    # each triplet has three ballots
    assert len(triplet) == 3
    # each ballot has same ammount of options
    assert len(triplet[0]) == len(triplet[1]) and len(triplet[1]) == len(triplet[2])

    total_options = len(triplet[0])
    res = [a + b + c for a, b, c in zip(triplet[0], triplet[1], triplet[2])]

    twos, ones = res.count(2), res.count(1)
    return twos == 1 and twos + ones == total_options


if __name__ == "__main__":
    total_options = 20
    total_voters = 100

    triplets = generate_triplets(total_options, total_voters)
    strips = strips_from_triplets(triplets)
    pprint(triplets)
    print("\nShuffled strips from triplets:")
    pprint(strips)

    total = 0
    for i in range(len(strips)):
        for j in range(i + 1, len(strips)):
            for k in range(j + 1, len(strips)):
                triplet = [strips[i], strips[j], strips[k]]
                if is_legal_triplet(triplet):
                    total += 1
            #  print(triplet, is_legal_triplet(triplet))

    print(total)
